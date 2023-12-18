import React, { useState } from 'react';

const SearchBar = ({ onSearch }) => {
  const [keyword, setKeyword] = useState('');

  const handleSearch = () => {
    // Simulate search logic. Replace with actual fetch call.
    // Example: fetch(`/search?keyword=${keyword}`)
    // .then(response => response.json())
    // .then(data => onSearch(data));
    onSearch();
  };

  return (
    <div>
      <h2>Search Bar</h2>
      <input type="text" placeholder="Search" value={keyword} onChange={(e) => setKeyword(e.target.value)} />
      <button onClick={handleSearch}>Search</button>
    </div>
  );
};

export default SearchBar;
