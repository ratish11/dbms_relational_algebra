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
    const[e1,setE1]=useState('')
    const[emp2,setEmp2]=useState('')
    const[e2,setE2]=useState('')
    const[query1,setQuery1]=useState([])
    const[query2,setQuery2]=useState([])
    const[query3,setQuery3]=useState([])
    const[query4,setQuery4]=useState([])
    const[query5,setQuery5]=useState([])
    const[query6,setQuery6]=useState([])
    const classes = useStyles();
    const [isLoading, setIsLoading] = useState(false);
    const [err, setErr] = useState('');
// query6
  const handleClick6=async (e)=>{
    setIsLoading(true);
    e.preventDefault()
    const edtls={e1,e2}

    try {
      const response = await fetch(`http://localhost:8080/requestData/query6/${encodeURIComponent(edtls.e1)}/${encodeURIComponent(edtls.e2)}`,{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify() 
       })
      if (!response.ok) {
        throw new Error(`Error! status: ${response.status}`);
      }

      const result = await response.json();
      console.log('result is: ', JSON.stringify(result, null, 4));
      setQuery6(result);
    } catch (err) {
      setErr(err.message);
    } finally {
      setIsLoading(false);
    }
  }
  return (

    <Container>
   {/* DoS6 */}
   <Paper elevation={3} style={paperStyle}>
    <h10>⦁	2 degrees: E1 {'-->'} D1 {'<--'} E3 {'-->'} D2 {'<--'} E2 (E1 and E3 work at D1 at the same time; E3 and E2 work at D2 at the same time)</h10>
    <h5 style={{ color : "#069" }}>SELECT DOS1.e1_emp, DOS1.e1_dept, DOS1.e1_from, DOS1.e1_to, DOS1.e3_emp, DOS1.e3_dept, DOS1.e3_from, DOS1.e3_to, DOS2.e2_emp, DOS2.e2_dept, DOS2.e2_from, DOS2.e2_to FROM (SELECT D1.emp_no AS e1_emp, D1.dept_no AS e1_dept, D1.from_date AS e1_from, D1.to_date AS e1_to, D3.emp_no AS e3_emp, D3.dept_no AS e3_dept, D3.from_date AS e3_from, D3.to_date AS e3_to FROM dept_emp D1 JOIN dept_emp D3 ON D1.dept_no=D3.dept_no AND (((D1.from_date{'<='}D3.from_date) AND ((D1.to_date{'>='}D3.from_date) OR (D1.to_date {'<='} D3.to_date))) OR ((D3.from_date{'<='}D1.from_date) AND ((D3.to_date{'>='}D1.from_date) OR (D3.to_date {'<='} D1.to_date))) OR ((D1.from_date {'>='} D3.from_date) AND (D1.to_date {'<='} D3.to_date)) OR ((D3.from_date {'>='} D1.from_date) AND (D3.to_date {'<='} D1.to_date))) WHERE D1.emp_no != D3.emp_no) DOS1 JOIN (SELECT D3.emp_no AS e3_emp, D3.dept_no AS e3_dept, D3.from_date AS e3_from, D3.to_date AS e3_to, D2.emp_no AS e2_emp, D2.dept_no AS e2_dept, D2.from_date AS e2_from, D2.to_date AS e2_to FROM dept_emp D2 JOIN dept_emp D3 ON D2.dept_no=D3.dept_no AND (((D2.from_date{'<='}D3.from_date) AND ((D2.to_date{'>='}D3.from_date) OR (D2.to_date {'<='} D3.to_date))) OR ((D3.from_date{'<='}D2.from_date) AND ((D3.to_date{'>='}D2.from_date) OR (D3.to_date {'<='} D2.to_date))) OR ((D2.from_date {'>='} D3.from_date) AND (D2.to_date {'<='} D3.to_date)) OR ((D3.from_date {'>='} D2.from_date) AND (D3.to_date {'<='} D2.to_date))) WHERE D2.emp_no != D3.emp_no) DOS2 ON DOS1.e3_emp = DOS2.e3_emp AND DOS1.e1_emp != DOS2.e2_emp AND DOS1.e3_dept != DOS2.e3_dept AND DOS1.e1_emp = :emp1 AND DOS2.e2_emp = :emp2 LIMIT 100;</h5>
      <h3 style={{color:"grey"}}><u>Provide Employee IDs</u></h3>
    <form className={classes.root} noValidate autoComplete="off">
    
      <TextField id="outlined-basic" label="First Employee ID" variant="outlined" fullWidth 
      value={e1}
      onChange={(e)=>setE1(e.target.value)}
      />
      <TextField id="outlined-basic" label="Second Employee ID" variant="outlined" fullWidth
      value={e2}
      onChange={(e)=>setE2(e.target.value)}
      />
      {err && <h2>{err}</h2>}
      <Button variant="contained" color="beige" onClick={handleClick6}>
        Submit
      </Button>
      {isLoading && <h2>Loading...</h2>}
      {query6.map(query6=>(
        <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}}  key={query6.e1_emp}>
          <table className="table">
            <thead>
              <tr>
                <th style={{margin:"10px",padding:"5px", textAlign:"left"}}>Emp1 ID</th>
                <th style={{margin:"10px",padding:"5px", textAlign:"left"}}>Department D1</th>
                <th style={{margin:"10px",padding:"5px", textAlign:"left"}}>Emp3 ID</th>
                <th style={{margin:"10px",padding:"5px", textAlign:"left"}}>Department D2</th>
                <th style={{margin:"10px",padding:"5px", textAlign:"left"}}>Emp2 ID</th>
              </tr>
            </thead>
          </table>
          <table className="table">
            <thead>
              <tr>
                <td style={{margin:"5px",padding:"25px", textAlign:"justify"}}>{query6.e1_emp}</td>
                <td style={{margin:"5px",padding:"25px", textAlign:"justify"}}>{query6.e1_dept}</td>
                <td style={{margin:"5px",padding:"25px", textAlign:"justify"}}>{query6.e3_emp}</td>
                <td style={{margin:"5px",padding:"25px", textAlign:"justify"}}>{query6.e2_dept}</td>
                <td style={{margin:"5px",padding:"25px", textAlign:"justify"}}>{query6.e2_emp}</td>
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