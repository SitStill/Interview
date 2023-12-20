import React from 'react';
import SurveyForm from '../components/SurveyForm';

const SubmitSurveyPage = () => {
  const handleSubmitSurvey = (surveyData) => {
    // Implement your logic for handling the survey data submission
    console.log('Submitting survey data:', surveyData);
  };

  return (
    <div>
      <h2>Submit Survey</h2>
      <SurveyForm onSubmitSurvey={handleSubmitSurvey} />
    </div>
  );
};

export default SubmitSurveyPage;
