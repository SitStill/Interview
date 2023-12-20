import React, { useState } from 'react';
import SearchBar from '../components/SearchBar';
import SurveyList from '../components/SurveyList';
import './SearchSurveyPage.css'; // Import your CSS file

const SearchSurveyPage = () => {
  const [surveys, setSurveys] = useState([]);
  const [searchType, setSearchType] = useState('all'); // 'all' or 'userId'

  const handleSearch = (keyword) => {
    console.log('keyword: ', keyword);
    // Ensure keyword is a string, and trim any leading or trailing whitespaces
    const sanitizedKeyword = typeof keyword === 'string' ? encodeURIComponent(keyword.trim()) : '';
    console.log('sanitizedKeyword: ', sanitizedKeyword);

    let url;
    if (searchType === 'all') {
      url = '/api/surveys';
    } else if (searchType === 'userId' && sanitizedKeyword) {
      url = `/api/surveys/${sanitizedKeyword}`;
    } else {
      url = '/api/surveys'; // Default to searching all surveys if userId is undefined or empty
    }
  
    fetch(url, {
      method: 'GET',
    })
      .then(response => response.json())
      .then(data => {
        setSurveys(data);
      })
      .catch(error => {
        console.error('Error searching surveys:', error);
      });
  };

  const handleSearchTypeChange = (type) => {
    setSearchType(type);

    // Add this line for debugging
    console.log('Current search type:', type);
  };

  const handleSearchAll = () => {
    // Trigger the "Search All" operation with an empty keyword
    if (searchType === 'all') {
      handleSearch('');
    }
  };

  return (
    <div>
      <h2>Search Surveys</h2>
      <div>
        <label>
          <input
            type="radio"
            value="all"
            checked={searchType === 'all'}
            onChange={() => handleSearchTypeChange('all')}
          />
          Search All
        </label>
        <label>
          <input
            type="radio"
            value="userId"
            checked={searchType === 'userId'}
            onChange={() => handleSearchTypeChange('userId')}
          />
          Search by User ID
        </label>
        {searchType === 'all' && (
          <button onClick={handleSearchAll}>Search All</button>
        )}
      </div>
      <div id="user-id-input" className={searchType === 'userId' ? 'visible' : ''}>
        <SearchBar onSearch={handleSearch} />
      </div>
      <SurveyList surveys={surveys} />
    </div>
  );
};

export default SearchSurveyPage;
