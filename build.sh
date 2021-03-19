#!/bin/bash
#################### 变量定义 ####################
# xxl-job-admin节点列表
xxl_containers=(xxl_job_admin1 xxl_job_admin2)


#################### 函数定义 ####################
# 获取服务器的ip
docker_ip() {
    docker inspect --format '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' "$@"
}

#################### docker-compose初始化 ####################
docker-compose down
docker-compose build
docker-compose up -d mysql

#################### 服务器初始化操作 ####################
# 这个操作的目的是尝试连接服务器, 如果连接失败, 就等待4s后重试, 直到等待mysql服务器就绪, 并且连接上为止
until docker exec mysql sh -c 'export MYSQL_PWD='$root_password'; mysql -u root -e ";"'
do
    echo "等待 mysql 连接中,请稍候,每 3s 尝试连接一次,可能会重试多次,直到容器启动完毕......"
    sleep 3
done

docker-compose up -d

echo -e "\033[42;34m finish success !!! \033[0m"