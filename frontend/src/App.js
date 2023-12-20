import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './components/Login';
import TransitionPage from './components/TransitionPage';
import SubmitSurveyPage from './components/SubmitSurveyPage';
import SearchSurveyPage from './components/SearchSurveyPage';
import './App.css';

const App = () => {
  const [token, setToken] = useState('');

  const handleLogin = (newToken) => {
    setToken(newToken);
  };

  const handleLogout = () => {
    setToken('');
  };

  return (
    <Router>
      <div className="app-container">
        {token ? (
          <>
            <header>
              <h1>Survey App</h1>
              <button onClick={handleLogout}>Logout</button>
            </header>
            <main>
              <Routes>
                <Route path="/submit" element={<SubmitSurveyPage />} />
                <Route path="/search" element={<SearchSurveyPage />} />
                <Route path="/" element={<TransitionPage />} />
              </Routes>
            </main>
          </>
        ) : (
          <Login onLogin={handleLogin} />
        )}
      </div>
    </Router>
  );
};

export default App;
