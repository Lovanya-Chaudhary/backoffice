                          Redis Window

-----------------------------------------------------------------------------------------------------------
                             Redis Server
-----------------------------------------------------------------------------------------------------------

1- Download Redis for Window (MSI or ZIp file)
https://github.com/ServiceStack/redis-windows/raw/master/downloads/redis-latest.zip
https://github.com/microsoftarchive/redis/releases

2- Unzip Redis folder This should create the following executables in folder:
	redis-server.exe
	redis-benchmark.exe
	redis-cli.exe
	redis-check-dump.exe
	redis-check-aof.exe

3- Start redis-server , This should be started with default port 6379

4- Start redis-cli , This will start Redis client which is connected with Redis server at 6379 port.

5- Test server connection by 'ping' command or set name value to server & get value for key.

6- Exit Client cli- exit 
Client must authenticate with Redis server before starting communication.
	1- Server must have requirepass  attrubute value in conf file
	2- Client first authenticate with server  AUTH password
	3- Start communication 
	   ping
	   set name Jai
	   get name
7- Exit Server - shut-down / Ctrl + C

-----------------------------------------------------------------------------------------------------------
           Redis Master-slaves nodes configuration
-----------------------------------------------------------------------------------------------------------


1- Copy 'redis.conf' file & create three duplicate file for three instances for Redis Server. One for Redis master & two for slave node.
Example:-
redis.windows1.conf
redis.windows2.conf
redis.windows3.conf

2- Update master node conf file. Set 'masterauth' parameter so that slave node can be authenticate & connect with master node. 
redis-server.exe redis.windows1.conf
Example-  
port 6379
masterauth password

3- Update slave node conf file. Set port , slaveof & masterauth parameters in slave conf file.
redis.windows2.conf
redis.windows3.conf

Example:- 
port 6380
masterauth password
slaveof 127.0.0.1 6379

4- Start Redis Master & slave nodes.
redis-server.exe redis.windows1.conf
redis-server.exe redis.windows2.conf
redis-server.exe redis.windows3.conf

-----------------------------------------------------------------------------------------------------------
           Redis Master-sentinel nodes configuration
-----------------------------------------------------------------------------------------------------------

1- Create sentinel.conf file for sentinel nodes 

2- Add Sentinel parameters 


port 26379
sentinel monitor master 127.0.0.1 6379 2
#sentinel auth-pass password
sentinel down-after-milliseconds master 5000
sentinel failover-timeout master 60000
sentinel parallel-syncs master 1

3- Create duplicate copy for other two sentinel node & update sentinel node port.

4- Start Redis Sentinel nodes for each sentinel conf file
redis-server.exe sentinel-1.conf --sentinel
redis-server.exe sentinel-2.conf --sentinel
redis-server.exe sentinel-3.conf --sentinel

5- Run Redis client to connect Sentinel node
redis-cli AUTH password
redis-cli -p 6379

sentinel get-master-addr-by-name master
redis-cli -p 6379 client list
redis-cli -p 6379 info
redis-cli -p 6379 monitor | grep INFO










redis-server --service-install --service-name redisService1 -port 10001 redis.windows.conf



redis-server.exe -service-run redis.windows-service-r1.conf
redis-server.exe -service-run redis.windows-service-r2.conf
redis-server.exe -service-run redis.windows-service-r3.conf


redis-server redis.windows1.conf
redis-server.exe sentinel-1.conf --sentinel
redis-server.exe sentinel-2.conf --sentinel




Reference Link 
https://github.com/ServiceStack/redis-windows
https://www.youtube.com/watch?v=vPcFJvvJgVA
https://thinkpalm.com/blogs/introduction-redis-sentinel-features-work/
http://download.redis.io/redis-stable/sentinel.conf
https://medium.com/@umutuluer/resolving-the-err-client-sent-auth-but-no-password-is-set-error-b81438d10843
https://github.com/MicrosoftArchive/redis/releases
https://redis.io/commands
https://fossies.org/linux/redis/sentinel.conf
https://serverfault.com/questions/722803/redis-error-noauth-authentication-required

https://docs.uipath.com/orchestrator/v2019-fastTrack/docs/redis-sentinel-configuration
https://www.objectrocket.com/blog/how-to/introduction-to-redis-sentinel/
https://jameshfisher.com/2019/01/08/how-to-run-redis-sentinel/
