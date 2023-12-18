// SearchSurveyPage.js
import React, { useState } from 'react';
import SearchBar from '../components/SearchBar';
import SurveyList from '../components/SurveyList';

const SearchSurveyPage = () => {
  const [surveys, setSurveys] = useState([]);

  const handleSearch = (keyword) => {
    // Simulate search logic. Replace with actual fetch call.
    // Example: fetch(`/search?keyword=${keyword}`)
    // .then(response => response.json())
    // .then(data => setSurveys(data));
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
