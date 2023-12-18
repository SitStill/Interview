// SurveyForm.js
import React, { useState } from 'react';

const SurveyForm = ({ onSubmitSurvey }) => {
  const [userId, setUserId] = useState('');
  const [question, setQuestion] = useState('');
  const [answer, setAnswer] = useState('');
  const [attachment, setAttachment] = useState(null); // Updated to use state for attachment

  const handleSubmit = () => {
    // Check if required fields are not empty
    if (!userId || !question || !answer) {
      alert('Please fill out all required fields.');
      return;
    }

    // Create surveyData object
    const surveyData = {
      userId,
      question,
      answer,
      attachment: attachment ? attachment.name : null,
    };

    // Call the onSubmitSurvey function with surveyData
    onSubmitSurvey(surveyData);
  };

  // Handle file input change
  const handleFileChange = (event) => {
    const file = event.target.files[0];
    setAttachment(file);
  };

  return (
    <div>
      <label>
        User ID:
        <input type="text" value={userId} onChange={(e) => setUserId(e.target.value)} />
      </label>
      <br />
      <label>
        Question:
        <input type="text" value={question} onChange={(e) => setQuestion(e.target.value)} />
      </label>
      <br />
      <label>
        Answer:
        <input type="text" value={answer} onChange={(e) => setAnswer(e.target.value)} />
      </label>
      <br />
      <label>
        Attachment:
        <input type="file" onChange={handleFileChange} />
      </label>
      <br />
      <button onClick={handleSubmit}>Submit Survey</button>
    </div>
  );
};

export default SurveyForm;
