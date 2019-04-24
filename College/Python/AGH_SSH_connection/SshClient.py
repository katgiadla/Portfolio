import paramiko,sys,json,os,time
from datetime import datetime, timedelta

#Imported paramiko library in order to create ssh client - server connection.

#Program connects to ssh server through user and password
#and its behaviour depends on what is written in Json config file.

class SshClient():
    client = None
    sftp = None

    def inicialization(self):
        with open("Config.json",'r') as configDataTransfer:
            test = json.load(configDataTransfer)


            hostname = test["address"]
            port = test["port"]
            username = input("username\n")
            password = input("password\n")
            mode = test["mode"]
            local_path = test["localPath"]
            remote_path = test["remotePath"]
            ignore = test["ignore"]

        client = paramiko.SSHClient()
        client.set_missing_host_key_policy(paramiko.WarningPolicy)
        client.set_missing_host_key_policy(paramiko.AutoAddPolicy)
        try:
            client.connect(hostname, port, username, password)
        except paramiko.ssh_exception.AuthenticationException:
            print("incorrect login or password")
            self.inicialization()
        self.sftp = client.open_sftp()


        #The core of the SshClient object


        while True:
            #mode = input("what do you want to do? \n overwrite|update|add_non_existing \n")
            if(mode == '1'):
                #string = input("insert path\n")
                files = self.sftp.listdir('/home/stud/is2016/xxx/serverssh/' )
                print(files)
            if(mode == "overwrite"):
                self.overwrite(local_path, remote_path,ignore)
            if(mode == "update"):
                self.update(local_path,remote_path,ignore)
            if(mode == "add_non_existing"):
                self.add_non_existing(local_path,remote_path,ignore)
            if(mode == '0'):
                self.close()



    def overwrite(self,local_path,remote_path,ignore):
        for name in os.listdir(local_path):
            isIgnored = False
            inputPath = remote_path + name
            inputRemotePath = remote_path + name
            inputLocalPath = local_path + name
            type =  inputPath.split('.')[-1]

            for i in range(0, len(ignore)):
                if (ignore[i] == type):
                    print(type+" in "+name+" is ignored"+'\n')
                    isIgnored = True
                    break
            if (isIgnored):
                continue;

            isFound = False
            for i in self.sftp.listdir('/home/stud/is2016/xxx/serverssh/'):
                if (i == name):
                    print("overwrite "+name+'\n')
                    self.sftp.put(inputLocalPath, inputRemotePath)
                    isFound = True
                    break

            if (not isFound):
                print("new file " + name+'\n')
                self.sftp.put(inputLocalPath, inputRemotePath)
                continue

    def update(self,local_path,remote_path,ignore):
        for name in os.listdir(local_path):
            isIgnored = False
            inputPath = remote_path + name
            inputRemotePath = remote_path + name
            inputLocalPath = local_path + name
            type = inputPath.split('.')[-1]

            for i in range(0, len(ignore)):
                if (ignore[i] == type):
                    print(type + " in " + name + " is ignored"+'\n')
                    isIgnored = True
                    break

            if isIgnored:
                continue;

            for i in self.sftp.listdir(remote_path):
                if(i == name):
                    utime = self.sftp.stat(inputRemotePath).st_mtime
                    last_modified_date = os.stat(inputLocalPath).st_mtime #datetime.fromtimestamp(os.path.getmtime(name))
                    last_modified = datetime.fromtimestamp(utime)
                    print('local file '+str(datetime.fromtimestamp(os.stat(inputLocalPath).st_mtime)))
                    print('server ssh '+str(last_modified))
                    print('utime '+str(utime))
                    print(' local file stempel '+str(os.stat(inputLocalPath).st_mtime))

                    if(last_modified_date>utime):
                        self.sftp.put(inputLocalPath, inputRemotePath)
                        print(name+" updated \n")
                        break
                    else:
                        print(name+" local file is older \n")
                        break

    def add_non_existing(self,local_path,remote_path,ignore):
        for name in os.listdir(local_path):
            isIgnored = False
            inputPath = remote_path + name
            inputRemotePath = remote_path + name
            inputLocalPath = local_path + name
            type = inputPath.split('.')[-1]

            for i in range(0, len(ignore)):
                # print(ignore[i]+" ignored type of file")
                if (ignore[i] == type):
                    print(type + " in " + name + " is ignored"+'\n')
                    isIgnored = True
                    break
            if (isIgnored):
                continue;

            exist = False
            for i in self.sftp.listdir('/home/stud/is2016/xxx/serverssh/' ):
                #lstatout = str(self.sftp.lstat(i)).split()[0]
                if i == name:
                    print("file " +name+" exist"+'\n')
                    exist = True
                    break;

            if not exist:
                self.sftp.put(inputLocalPath, inputRemotePath)
                print("files doesnt exist, creating: "+name+"\n")
                continue

    def close(self):

        if self.sftp != None:
            self.sftp.close()
        if self.client != None:
            self.client.close()
        #Server.close(self)
        sys.exit()

s = SshClient()
s.inicialization()