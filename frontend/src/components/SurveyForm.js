import React, { useState } from 'react';

const SurveyForm = ({ onSubmitSurvey }) => {
  const [userId, setUserId] = useState('');
  const [question, setQuestion] = useState('');
  const [answer, setAnswer] = useState('');

  const handleSubmit = () => {
    // Simulate survey submission logic. Replace with actual fetch call.
    // Example: fetch('/submit-survey', { method: 'POST', body: JSON.stringify({ userId, question, answer }) })
    // .then(response => response.json())
    // .then(data => console.log(data));
    onSubmitSurvey();
  };

  return (
    <div>
      <h2>Survey Form</h2>
      <input type="text" placeholder="User ID" value={userId} onChange={(e) => setUserId(e.target.value)} />
      <input type="text" placeholder="Question" value={question} onChange={(e) => setQuestion(e.target.value)} />
      <input type="text" placeholder="Answer" value={answer} onChange={(e) => setAnswer(e.target.value)} />
      <button onClick={handleSubmit}>Submit Survey</button>
    </div>
  );
};

export default SurveyForm;
