// SubmitSurveyPage.js
import React from 'react';
import SurveyForm from '../components/SurveyForm';

const SubmitSurveyPage = () => {
//   const handleSubmitSurvey = (surveyData) => {
//     // Make a POST request to the backend to submit the survey
//     fetch('/submit-survey', {
//       method: 'POST',
//       headers: {
//         'Content-Type': 'application/json',
//       },
//       body: JSON.stringify(surveyData),
//     })
//       .then(response => response.json())
//       .then(data => {
//         console.log(data);
//         // Optionally handle the response from the server
//       })
//       .catch(error => {
//         console.error('Error submitting survey:', error);
//       });
//   };

  const handleSubmitSurvey = (surveyData) => {
    fetch('/api/submit-survey', { // Update the URL to match the Nginx configuration
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(surveyData),
    })
      .then(response => response.json())
      .then(data => {
        console.log(data);
        // Optionally handle the response from the server
      })
      .catch(error => {
        console.error('Error submitting survey:', error);
      });
  };

  return (
    <div>
      <h2>Submit Survey</h2>
      <SurveyForm onSubmitSurvey={handleSubmitSurvey} />
    </div>
  );
};

export default SubmitSurveyPage;
