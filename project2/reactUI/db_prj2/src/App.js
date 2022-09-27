import './App.css';
import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import AppBar from './components/AppBar';
import styled from "styled-components";

import Query1 from './Query1';
import Query2 from './Query2';
import Query3 from './Query3';
import Query4 from './Query4';
import Query5 from './Query5';
import Query6 from './Query6';

const StyledLink = styled(Link)`
  color: Black;
  text-decoration: none;
  margin: 1rem;
  position: relative;
  border-style: solid;
  border-color: black
`;

function App(){  
    return ( 
      <Router>
        <div>
            <AppBar/>
            <h1>Click on the query to be displayed</h1>
            <div> <StyledLink to="/query1">Query 1</StyledLink>
            <StyledLink to="/query2">Query 2</StyledLink>
            <StyledLink to="/query3">Query 3</StyledLink>
            <StyledLink to="/query4">Query 4</StyledLink>
            <StyledLink to="/query5">Query 5</StyledLink>
            <StyledLink to="/query6">Query 6</StyledLink><br /></div>
          <Routes>
          
          <Route path = "/query1" element = {<Query1 />} />
          <Route path = "/query2" element = {<Query2 />} />
          <Route path = "/query3" element = {<Query3 />} />
          <Route path = "/query4" element = {<Query4 />} />
          <Route path = "/query5" element = {<Query5 />} />
          <Route path = "/query6" element = {<Query6 />} />
          </Routes>

        </div>  
      </Router>

    )  
  } 

export default App;