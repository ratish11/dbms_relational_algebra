import './App.css';
import React from "react";
//export * from "react-router";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import AppBar from './components/AppBar';
import OutlinedCard from './components/Card';
import TitlebarBelowImageList from './components/TitlebarBelowImageList';
import styled from "styled-components";
//
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
        <div>
            <AppBar/>
            <div style={{border: "0.1px solid black"}} className="staticRight">
                <TitlebarBelowImageList/>
            </div>
            <h1 className="headBelow">Choose your path of Exploration   </h1>
            <div>
            <OutlinedCard/>
             </div>
        </div>
      </div>
      </Router>

    )  
  } 

export default App;