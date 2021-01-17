#/bin/sh

FTP_EXE=$1
FTP_USER=$2
FTP_PASSWD=$3
FTP_SERVER=$4
FTP_UPLOAD="$FTP_EXE -u ftp://$FTP_USER:$FTP_PASSWD@$FTP_SERVER/testdata"

echo $FTP_UPLOAD

export PATH=$PATH:/fs/etfs/app/sbin/:/fs/etfs/app/usr/bin

root_dir="/tmp"

app_prio_filename="app_prio.txt"
starter_filename="starter.logs"
emmc_usage_filename="emmc_usage.txt"
version_filename="version.txt"
cpu_ram_info_filename="cpu_ram_info.txt"

if [ -e /fs/etfs/use_default_socket ]; then
	echo "default socket"
else
	export SOCK=/alt
fi

echo "Start Get Test Data Root Dir: "$root_dir

echo "Start Get Version Info"
cat /pps/hwinfo | grep "HWVariant" > $root_dir/$version_filename
cat /etc/version.txt | grep version >> $root_dir/$version_filename

echo "Upload Version Info"
$FTP_UPLOAD/$version_filename $root_dir/$version_filename

echo "Start Get Process Prio Info"
pidin -F "%a %b %N %p %h" > $root_dir/$app_prio_filename 
$FTP_UPLOAD/$app_prio_filename $root_dir/$app_prio_filename

echo "Start Get Starter Info"
cat /dev/starter/status > $root_dir/$starter_filename

$FTP_UPLOAD/$starter_filename $root_dir/$starter_filename

echo "Start Get Emmc Usage Info"
df -h > $root_dir/$emmc_usage_filename

$FTP_UPLOAD/$emmc_usage_filename $root_dir/$emmc_usage_filename

echo "copy cpu ram info file"
$FTP_UPLOAD/cpu_ram_info.txt /tmp/cpu_ram_info.txt

rm -f $root_dir/$version_filename
rm -f $root_dir/$app_prio_filename
rm -f $root_dir/$starter_filename
rm -f $root_dir/$emmc_usage_filename

sync

echo "Test Data Get End"

