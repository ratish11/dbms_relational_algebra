import React, { useEffect, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import { Container ,Paper,Button} from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
     
    },
  },
}));

export default function Query() {
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[query2,setQuery2]=useState([])
    const classes = useStyles();
    const [isLoading, setIsLoading] = useState(false);

    useEffect(()=>{
        fetch(`http://localhost:8080/requestData/query2`)
        .then(res=>res.json())
        .then((res2)=>{
          setQuery2(res2);
        }
      )
      },[])

      return (
        <Container>
      <Paper elevation={3} style={paperStyle}>
      <h10>⦁	List manager(s) who holds office for the longest duration</h10>
      <h5 style={{ color : "#069" }}>SELECT CONCAT(last_name, " ", first_name) AS Name FROM employees WHERE emp_no IN (SELECT emp_no FROM dept_manager WHERE datediff(to_date, from_date) IN (SELECT MAX(datediff(to_date, from_date)) AS term FROM dept_manager));</h5>
      </Paper>
      <Paper elevation={3} style={paperStyle}>
  
        {query2.map(query2=>(
          <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}}  key={query2.name}>
           Name : {query2.name}
          </Paper>
        ))
        }
      </Paper>
      </Container>
      );
    }