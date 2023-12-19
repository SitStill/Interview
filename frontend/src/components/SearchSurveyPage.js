// SearchSurveyPage.js
import React, { useState } from 'react';
import SearchBar from '../components/SearchBar';
import SurveyList from '../components/SurveyList';

const SearchSurveyPage = () => {
  const [surveys, setSurveys] = useState([]);

  const handleSearch = (keyword) => {
    fetch(`/api/search-surveys?keyword=${keyword}`, { // Update the URL to match the Nginx configuration
      method: 'GET',
    })
      .then(response => response.json())
      .then(data => {
        console.log(data);
        // Optionally handle the response from the server
      })
      .catch(error => {
        console.error('Error searching surveys:', error);
      });
  };

  return (
    <div>
      <h2>Search Surveys</h2>
      <SearchBar onSearch={handleSearch} />
      <SurveyList surveys={surveys} />
    </div>
  );
};

export default SearchSurveyPage;
