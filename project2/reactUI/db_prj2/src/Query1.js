/*import React from 'react';


const Query1 = () => {  
   
    return <h1>Query1</h1>  
  
}
export default Query1;*/

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
    const[query1,setQuery1]=useState([])
    const classes = useStyles();
    const [isLoading, setIsLoading] = useState(false);

    
    useEffect(()=>{
        fetch(`http://localhost:8080/requestData/query1`)
        .then(res=>res.json())
        .then((res1)=>{
          setQuery1(res1);
        }
      )
      },[])

      return (
      <Container>
    <Paper elevation={3} style={paperStyle}>
        {/* <h5 style={{ color : "#069" }}></h5> */}
        <h10>⦁	List department(s) with maximum ratio of average female salaries to average men salaries </h10>
        <h5 style={{ color : "#069" }}>SELECT fin.dept_name, fin.ratio FROM (SELECT Male.dept_name,(Fmale.fsal/Male.msal) AS ratio FROM ((SELECT dept_name, avg(salary) AS fsal, gender FROM departments JOIN dept_emp ON departments.dept_no = dept_emp.dept_no JOIN employees ON dept_emp.emp_no = employees.emp_no JOIN salaries ON employees.emp_no = salaries.emp_no GROUP BY dept_name,gender HAVING gender = 'F') Fmale,(SELECT dept_name, avg(salary) AS msal, gender FROM  departments JOIN dept_emp ON departments.dept_no = dept_emp.dept_no JOIN employees ON dept_emp.emp_no = employees.emp_no JOIN salaries ON employees.emp_no = salaries.emp_no GROUP BY dept_name,gender HAVING gender = 'M') Male) WHERE Male.dept_name = Fmale.dept_name) fin WHERE fin.ratio = (SELECT MAX(final.ratio) FROM (SELECT Male.dept_name,(Fmale.fsal/Male.msal) AS ratio FROM ((SELECT dept_name, avg(salary) AS fsal, gender FROM departments JOIN dept_emp ON departments.dept_no = dept_emp.dept_no JOIN employees ON dept_emp.emp_no = employees.emp_no JOIN salaries ON employees.emp_no = salaries.emp_no GROUP BY dept_name,gender HAVING gender = 'F') Fmale,(SELECT dept_name, avg(salary) AS msal, gender FROM  departments JOIN dept_emp ON departments.dept_no = dept_emp.dept_no JOIN employees ON dept_emp.emp_no = employees.emp_no JOIN salaries ON employees.emp_no = salaries.emp_no GROUP BY dept_name,gender HAVING gender = 'M') Male) WHERE Male.dept_name = Fmale.dept_name) final );</h5>
    </Paper>
    <Paper elevation={3} style={paperStyle}>

      {query1.map(query1=>(
        <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}}  key={query1.dept_name}>
         Department Name : {query1.dept_name}<br/>
         Maximum Ratio : {query1.ratio}
        </Paper>
      ))
      }
    </Paper>
      </Container>
      );
    }