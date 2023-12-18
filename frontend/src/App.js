import React, { useState } from 'react';
import Login from './components/Login';
import SurveyForm from './components/SurveyForm';
import SurveyList from './components/SurveyList';
import SearchBar from './components/SearchBar';


const App = () => {
  const [token, setToken] = useState('');
  const [surveys, setSurveys] = useState([]);

  const handleLogin = (newToken) => {
    setToken(newToken);
  };

  const handleSubmitSurvey = () => {
    // Simulate survey submission logic. Replace with actual fetch call.
    // Example: fetch('/submit-survey', { method: 'POST', body: JSON.stringify({ userId, question, answer }) })
    // .then(response => response.json())
    // .then(data => {
    //    console.log(data);
    //    // Fetch updated survey list after submission
    //    fetch('/surveys')
    //      .then(response => response.json())
    //      .then(data => setSurveys(data));
    // });
    console.log("Survey submitted");
  };

  const handleSearch = () => {
   // Simulate search logic. Replace with actual fetch call.
    // Example: fetch(`/search?keyword=${keyword}`)
    // .then(response => response.json())
    // .then(data => setSurveys(data));
    console.log("Searching surveys");
  };

  return (
    <div>
      {token ? (
        <>
          <SurveyForm onSubmitSurvey={handleSubmitSurvey} />
          <SurveyList surveys={surveys} />
          <SearchBar onSearch={handleSearch} />
        </>
      ) : (
        <Login onLogin={handleLogin} />
      )}
    </div>
  );
};

export default App;
