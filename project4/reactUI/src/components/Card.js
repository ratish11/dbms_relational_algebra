import * as React from 'react';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import { CardActionArea } from '@mui/material';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import styled from "styled-components";
import { BrowserRouter as Router, Route, Switch, Link } from "react-router-dom";
import { makeStyles } from '@material-ui/core/styles';

import Query1 from '../Query1';
import Query2 from '../Query2';
import Query3 from '../Query3';
import Query4 from '../Query4';


const StyledLink = styled(Link)`
  color: Black;
  text-decoration: none;
  margin: 1rem;
  position: relative;
  border-style: solid;
  border-color: black
`;

const bull = (
  <Box
    component="span"
    sx={{ display: 'inline-block', mx: '2px', transform: 'scale(0.8)' }}
  >
    •
  </Box>
);

const card1 = (
  <React.Fragment>
  <Card sx={{ maxWidth: 1000 }}>
   <CardActionArea href="http://localhost:8080/requestData/query1">
    <CardContent>
      <Typography sx={{ fontSize: 18 }} color="#061b23" >
        Seasonwise World Drivers Championship
      </Typography>
    </CardContent>
   </CardActionArea>
   </Card>
  </React.Fragment>
);

const card2 = (
  <React.Fragment>
  <Card sx={{ maxWidth: 1000 }}>
    <CardActionArea href="http://localhost:8080/requestData/query2">
    <CardContent>
      <Typography sx={{ fontSize: 18 }} color="#061b23" gutterBottom>
            Drivers who surpassed the speed of 250 mph in a Lap.
      </Typography>
    </CardContent>
    </CardActionArea>
    </Card>
  </React.Fragment>
);

const card3 = (
  <React.Fragment>
  <Card sx={{ maxWidth: 1000 }}>
    <CardActionArea href="http://localhost:8080/requestData/query3">
    <CardContent>
      <Typography sx={{ fontSize: 18 }} color="#061b23" gutterBottom>
        Seasonal Constructor Champion.
      </Typography>
    </CardContent>
    </CardActionArea>
    </Card>
  </React.Fragment>
);

const card4 = (
  <React.Fragment>
  <Card sx={{ maxWidth: 1000 }}>
      <CardActionArea href="http://localhost:8080/requestData/query4">
    <CardContent>
      <Typography sx={{ fontSize: 18 }} color="#061b23" gutterBottom>
        Driver champions that don\'t belong to the Champion Constructor Team
      </Typography>
    </CardContent>
    </CardActionArea>
   </Card>
  </React.Fragment>
);

export default function OutlinedCard() {

  return (
    <Box sx={{ minWidth: 275 }}>
    <Router>
      <Card variant="outlined">{card1}</Card>
      <Card variant="outlined">{card2}</Card>
      <Card variant="outlined">{card3}</Card>
      <Card variant="outlined">{card4}</Card>
      <Route path = "/query1" element = {<Query1 />} />
      <Route path = "/query2" element = {<Query2 />} />
      <Route path = "/query3" element = {<Query3 />} />
      <Route path = "/query4" element = {<Query4 />} />
      </Router>
    </Box>
  );
}