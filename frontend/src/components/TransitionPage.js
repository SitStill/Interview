// TransitionPage.js
import React from 'react';
import { Link } from 'react-router-dom';

const TransitionPage = () => {
  return (
    <div>
      <h2>Welcome! Choose an option:</h2>
      <div>
        <Link to="/submit">Submit Survey</Link>
      </div>
      <div>
        <Link to="/search">Search Surveys</Link>
      </div>
    </div>
  );
};

export default TransitionPage;
