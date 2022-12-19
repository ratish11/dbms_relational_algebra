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
    const[query4,setQuery4]=useState([])
    const classes = useStyles();
    const [isLoading, setIsLoading] = useState(false);

    useEffect(()=>{
        fetch(`http://localhost:8080/requestData/query4`)
        .then(res=>res.json())
        .then((res4)=>{
          setQuery4(res4);
        }
      )
      },[])
        return (
      
        <Container>
           
           <Paper elevation={3} style={paperStyle}><h10>⦁	List employees, who are female, born before Jan 1, 1990, makes more than 80K annually and hold a manager position</h10>
                <h5 style={{ color : "#069" }}>SELECT DISTINCT(FMGR.Name) FROM (SELECT FE.emp_no,FE.birth_date, CONCAT(FE.last_name, " ", FE.first_name) AS Name, FE.gender FROM (SELECT * FROM employees WHERE gender='F' AND  birth_date{'<'}'1990-01-01') FE JOIN dept_manager DE ON FE.emp_no = DE.emp_no) FMGR JOIN salaries S ON FMGR.emp_no = S.emp_no WHERE S.salary{'>'}80000;</h5>
                </Paper>
                <Paper elevation={3} style={paperStyle}>

                {query4.map(query4=>(
                    <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}}  key={query4.name}>
                    Name : {query4.name}
                    </Paper>
                ))
                }
                </Paper>
       </Container>
       );
    }
        
                