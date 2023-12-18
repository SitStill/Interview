import React, { useState } from 'react';
import './Login.css'; // 新建 Login.css 文件

const Login = ({ onLogin }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = () => {
    // 模拟登录逻辑，实际应用中应该发送请求到后端
    const username = '123';
    const password = '123';
  
    // 模拟验证逻辑
    if (username === '123' && password === '123') {
      // 登录成功，调用 onLogin 函数并传递模拟的 token
      onLogin('fake.token');
    } else {
      // 登录失败，可以添加错误处理逻辑
      console.log('Authentication failed');
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
      <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <button onClick={handleLogin}>Login</button>
    </div>
  );
};

export default Login;
