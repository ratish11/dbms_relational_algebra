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
    const[emp1,setEmp1]=useState('')
    const[emp2,setEmp2]=useState('')
    const[query5,setQuery5]=useState([])
    const classes = useStyles();
    const [isLoading, setIsLoading] = useState(false);
    const [err, setErr] = useState('');

    const handleClick=async (e)=>{
        setIsLoading(true);
        e.preventDefault()
        const empdtls={emp1,emp2}
        console.log(empdtls)
    
        try {
          const response = await fetch(`http://localhost:8080/requestData/query5/${encodeURIComponent(empdtls.emp1)}/${encodeURIComponent(empdtls.emp2)}`,{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify() 
           })
          if (!response.ok) {
            throw new Error(`Error! status: ${response.status}`);
          }
    
          const result = await response.json();
          console.log('result is: ', JSON.stringify(result, null, 4));
          setQuery5(result);
        } catch (err) {
          setErr(err.message);
        } finally {
          setIsLoading(false);
        }
      }

    return (
      <Container>
{/* Degree of Separation */}
<Paper elevation={3} style={paperStyle}>
    <h10>⦁	1 degree: E1 {'-->'} D1 {'<--'} E2 (E1 and E2 work at department D1 at the same time)</h10>
    <h5 style={{ color : "#069" }}>SELECT D1.emp_no AS e1_emp, D1.dept_no AS e1_dept, D1.from_date AS e1_from, D1.to_date AS e1_to, D2.emp_no AS e2_emp, D2.dept_no AS e2_dept, D2.from_date AS e2_from, D2.to_date AS e2_to  FROM dept_emp D1 JOIN dept_emp D2 ON D1.dept_no=D2.dept_no AND (((D1.from_date{'<='}D2.from_date) AND ((D1.to_date{'>='}D2.from_date) OR (D1.to_date {'<='} D2.to_date))) OR ((D2.from_date{'<='}D1.from_date) AND ((D2.to_date{'>='}D1.from_date) OR (D2.to_date {'<='} D1.to_date))) OR ((D1.from_date {'>='} D2.from_date) AND (D1.to_date {'<='} D2.to_date)) OR ((D2.from_date{'>='} D1.from_date) AND (D2.to_date {'<='} D1.to_date))) WHERE NOT D1.emp_no = D2.emp_no AND D1.emp_no = :emp1 AND D2.emp_no= :emp2 ;</h5>
      <h3 style={{color:"grey"}}><u>Provide Employee IDs</u></h3>

    <form className={classes.root} noValidate autoComplete="off">
    
      <TextField id="outlined-basic" label="First Employee ID" variant="outlined" fullWidth 
      value={emp1}
      onChange={(e)=>setEmp1(e.target.value)}
      />
      <TextField id="outlined-basic" label="Second Employee ID" variant="outlined" fullWidth
      value={emp2}
      onChange={(e)=>setEmp2(e.target.value)}
      />
      {/* <div>{emp1}</div>
      <div>{emp2}</div> */}
      {err && <h2>{err}</h2>}
      <Button variant="contained" color="beige" onClick={handleClick}>
        Submit
      </Button>
      {isLoading && <h2>Loading...</h2>}
      {query5.map(query5=>(
        <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}}  key={query5.e1_emp}>
          <table className="table">
            <thead>
              <tr>
                <th>Emp1 ID</th>
                <th>Department</th>
                <th>Emp2 ID</th>
              </tr>
            </thead>
          </table>
          <table className="table">
            <thead>
              <tr>
                <td>{query5.e1_emp}</td>
                <td><spacing>{query5.e1_dept}</spacing></td>
                <td>{query5.e2_emp}</td>
              </tr>
            </thead>
          </table>
        </Paper>
      ))
      }
    </form>

    </Paper>
    </Container>
  );
}