
#!/bin/bash
# Set variables for SSH parameters

# Receive parameter from outside file
phoneNumber="$1"
# Remove the '+62' prefix
phoneNumber="${phoneNumber#*+62}"

jumphostUser="mncnow"
jumphostHost="10.10.20.20"
jumphostPassword="Welcome.21!--"
privateKey="./p-sms-otp-db.pem"

dbUsername="qa_vplus" 
dbName="sms_gateway"
dbHost="10.10.128.146"
dbPort="5432"
dbPassword="qacredential"
SQLQuery="select otp from smsotp s where msisdn like '%$phoneNumber' and is_used=false order by created_at desc limit 1;"

# Connect to the jumphost with the private key and get the OTP value
otpValue=$(/opt/homebrew/Cellar/sshpass/1.06/bin/sshpass -p "$jumphostPassword" ssh -i "$privateKey" "$jumphostUser@$jumphostHost" <<-EOF
  psql -U "$dbUsername" -d "$dbName" -h "$dbHost" -p "$dbPort" -t -c "$SQLQuery" | grep -oP '\b\d+\b'
EOF
)

# Extract the last line from otpValue
lastLine=$(echo "$otpValue" | tail -n 1)

# # Print the OTP value from the last line
echo "$lastLine" | grep -o '[0-9]\+'