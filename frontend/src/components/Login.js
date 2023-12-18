import React, { useState } from 'react';

const Login = ({ onLogin }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = () => {
    // Simulate login logic. Replace with actual fetch call.
    // Example: fetch('/login', { method: 'POST', body: JSON.stringify({ username, password }) })
    // .then(response => response.json())
    // .then(data => onLogin(data.token)); // Assuming the backend returns a token
    onLogin('fake.token');
  };

  return (
    <div>
      <h2>Login</h2>
      <input type="text" placeholder="Username" value={username} onChange={(e) => setUsername(e.target.value)} />
      <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <button onClick={handleLogin}>Login</button>
    </div>
  );
};

export default Login;
