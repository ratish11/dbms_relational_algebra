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
    const[query3,setQuery3]=useState([])
    const classes = useStyles();
    const [isLoading, setIsLoading] = useState(false);
    
    useEffect(()=>{
        fetch(`http://localhost:8080/requestData/query3`)
        .then(res=>res.json())
        .then((res3)=>{
          setQuery3(res3);
        }
      )
      },[])
      return (
<Container>
<Paper elevation={3} style={paperStyle}><h10>⦁	For each department, list number of employees born in each decade and their average salaries</h10>
    <h5 style={{ color : "#069" }}>SELECT R.dept_no, R.decade, COUNT(R.emp_no) AS Count_of_Emp, AVG(R.salary) AS Average_Salary FROM (SELECT EDS.emp_no,EDS.decade, EDS.salary,DE.dept_no FROM (SELECT EMPED.emp_no,EMPED.decade,S.salary FROM (SELECT emp_no,(YEAR(birth_date) DIV 10)*10 AS decade FROM employees) EMPED JOIN salaries S ON EMPED.emp_no = S.emp_no) EDS JOIN dept_emp DE ON EDS.emp_no=DE.emp_no) R GROUP BY R.dept_no,R.decade ORDER BY R.dept_no;</h5>
</Paper>

    <Paper elevation={3} style={paperStyle}>

      {query3.map(query3=>(
        <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}}  key={query3.dept_no}>
         Department No : {query3.dept_no}<br/>
         Decade of the Employees : {query3.decade}<br/>
         Avarage Salaries of Employees : {query3.average_Salary}<br/>
         Count of Employees in the Department : {query3.count_of_Emp}
        </Paper>
      ))
      }
    </Paper>
</Container>  
 );
}    