# 使用官方 Node 镜像，指定版本
FROM node:14

# 在容器中创建一个工作目录
WORKDIR /usr/src/app

# 复制 package.json 和 package-lock.json（如果存在）到工作目录
COPY package*.json ./

# 清除 npm 缓存
RUN npm cache clean --force

# 安装项目依赖
RUN npm install

# 将应用的所有文件复制到工作目录
COPY . .

# 构建应用
RUN npm run build

# 暴露应用运行的端口
EXPOSE 3000

# 定义容器启动命令
CMD ["npm", "start"]