
import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import {Container, Row, Col, Card, Form } from "react-bootstrap";
//import { withRouter } from "react-router";
//import Sidebar from "./sideBar.js";
//import '../Dashboard.css'

import logo from "../public/f1.jpg";

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
    marginLeft: theme.spacing(3),
  },
  logo: {
      maxWidth: 100,
    },
  style: {
    background : '#f81d20',
  },
}));


//const Dashboard = Dash;
//export const Dashboard

export default function Appbar() {
  const classes = useStyles();


  return (
    <div className={classes.root}>
      <AppBar position="static" className={classes.style}>
        <Toolbar>
            <IconButton
                size="large"
                edge="start"
                color="inherit"
                aria-label="menu"
                sx={{ mr: 2 }}
            >
            <MenuIcon />
            </IconButton>

            <img src={logo} alt="logo" className={classes.logo} />
          <Typography variant="h6" className={classes.title}>
            A study on Slipstreams and drags for an F1 race champion
          </Typography>
          {/* <Button color="inherit">Login</Button> */}
        </Toolbar>
      </AppBar>
    </div>
  );
}
export const Dash = props => {
      return (
          <>
           <Container fluid>
                  <Row>
                      <Col xs={2} id="sidebar-wrapper">
                        <Sidebar />
                      </Col>
                      <Col  xs={10} id="page-content-wrapper">
                          this is a test
                      </Col>
                  </Row>

              </Container>
          </>
          );
    };
