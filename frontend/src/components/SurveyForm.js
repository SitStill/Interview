import React, { useState } from 'react';

const SurveyForm = ({ onSubmitSurvey }) => {
  const [userId, setUserId] = useState('');
  const [question, setQuestion] = useState('');
  const [answer, setAnswer] = useState('');
  const [attachment, setAttachment] = useState(null);

  const handleFileChange = (event) => {
    const file = event.target.files[0];
    setAttachment(file);
  };

  const handleSubmit = async () => {

    const formData = new FormData();
    formData.append('userId', userId);
    formData.append('question', question);
    formData.append('answer', answer);

    // 添加附件（如果有）
    if (attachment) {
        formData.append('attachment', attachment);
    }

    // 使用 fetch 发送 POST 请求
    try {
      const response = await fetch('/api/submit-survey', {
        method: 'POST',
        body: formData,
      });

      if (response.ok) {
        const responseData = await response.json();
        console.log('Survey submitted successfully:', responseData);
        // 在这里可以执行成功提交后的其他操作，例如重定向到另一个页面
        window.location.href = '/';
      } else {
        console.error('Failed to submit survey:', response.statusText);
      }
    } catch (error) {
      console.error('Error during survey submission:', error);
    }
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
