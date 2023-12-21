import React from 'react';

const SurveyList = ({ surveys }) => {
  return (
    <div>
      <h2>Survey List</h2>
      <ul>
        {surveys.map((survey) => (
          <li key={survey.id}>
            <p>User ID: {survey.userId}</p>
            <p>Question: {survey.question}</p>
            <p>Answer: {survey.answer}</p>
            {survey.attachment && (
                          <p>
                            Attachment:
                            {/* Assuming attachment is a file path; adjust accordingly */}
                            <a href={`/api/attachments/${attachmentPath}`} target="_blank" rel="noopener noreferrer">
                              View Attachment
                            </a>
                          </p>
                        )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default SurveyList;
