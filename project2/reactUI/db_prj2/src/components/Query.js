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

useEffect(()=>{
  fetch(`http://localhost:8080/requestData/query1`)
  .then(res=>res.json())
  .then((res1)=>{
    setQuery1(res1);
  }
)
},[])

useEffect(()=>{
  fetch(`http://localhost:8080/requestData/query2`)
  .then(res=>res.json())
  .then((res2)=>{
    setQuery2(res2);
  }
)
},[])

useEffect(()=>{
  fetch(`http://localhost:8080/requestData/query3`)
  .then(res=>res.json())
  .then((res3)=>{
    setQuery3(res3);
  }
)
},[])

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
    <h10>⦁	List department(s) with maximum ratio of average female salaries to average men salaries </h10>
    <h5 style={{ color : "#069" }}>SELECT fin.dept_name, fin.ratio FROM (SELECT Male.dept_name,(Fmale.fsal/Male.msal) AS ratio FROM ((SELECT dept_name, avg(salary) AS fsal, gender FROM departments JOIN dept_emp ON departments.dept_no = dept_emp.dept_no JOIN employees ON dept_emp.emp_no = employees.emp_no JOIN salaries ON employees.emp_no = salaries.emp_no GROUP BY dept_name,gender HAVING gender = 'F') Fmale,(SELECT dept_name, avg(salary) AS msal, gender FROM  departments JOIN dept_emp ON departments.dept_no = dept_emp.dept_no JOIN employees ON dept_emp.emp_no = employees.emp_no JOIN salaries ON employees.emp_no = salaries.emp_no GROUP BY dept_name,gender HAVING gender = 'M') Male) WHERE Male.dept_name = Fmale.dept_name) fin WHERE fin.ratio = (SELECT MAX(final.ratio) FROM (SELECT Male.dept_name,(Fmale.fsal/Male.msal) AS ratio FROM ((SELECT dept_name, avg(salary) AS fsal, gender FROM departments JOIN dept_emp ON departments.dept_no = dept_emp.dept_no JOIN employees ON dept_emp.emp_no = employees.emp_no JOIN salaries ON employees.emp_no = salaries.emp_no GROUP BY dept_name,gender HAVING gender = 'F') Fmale,(SELECT dept_name, avg(salary) AS msal, gender FROM  departments JOIN dept_emp ON departments.dept_no = dept_emp.dept_no JOIN employees ON dept_emp.emp_no = employees.emp_no JOIN salaries ON employees.emp_no = salaries.emp_no GROUP BY dept_name,gender HAVING gender = 'M') Male) WHERE Male.dept_name = Fmale.dept_name) final );</h5>

    <Paper elevation={3} style={paperStyle}>

      {query1.map(query1=>(
        <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}}  key={query1.dept_name}>
         Department Name : {query1.dept_name}<br/>
         Maximum Ratio : {query1.ratio}
        </Paper>
      ))
      }
    </Paper>
    <h10>⦁	List manager(s) who holds office for the longest duration</h10>
    <h5 style={{ color : "#069" }}>SELECT CONCAT(last_name, " ", first_name) AS Name FROM employees WHERE emp_no IN (SELECT emp_no FROM dept_manager WHERE datediff(to_date, from_date) IN (SELECT MAX(datediff(to_date, from_date)) AS term FROM dept_manager));</h5>

    <Paper elevation={3} style={paperStyle}>

      {query2.map(query2=>(
        <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}}  key={query2.name}>
         Name : {query2.name}
        </Paper>
      ))
      }
    </Paper>

    <h10>⦁	For each department, list number of employees born in each decade and their average salaries</h10>
    <h5 style={{ color : "#069" }}>SELECT R.dept_no, R.decade, COUNT(R.emp_no) AS Count_of_Emp, AVG(R.salary) AS Average_Salary FROM (SELECT EDS.emp_no,EDS.decade, EDS.salary,DE.dept_no FROM (SELECT EMPED.emp_no,EMPED.decade,S.salary FROM (SELECT emp_no,(YEAR(birth_date) DIV 10)*10 AS decade FROM employees) EMPED JOIN salaries S ON EMPED.emp_no = S.emp_no) EDS JOIN dept_emp DE ON EDS.emp_no=DE.emp_no) R GROUP BY R.dept_no,R.decade ORDER BY R.dept_no;</h5>

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
    <h10>⦁	List employees, who are female, born before Jan 1, 1990, makes more than 80K annually and hold a manager position</h10>
    <h5 style={{ color : "#069" }}>SELECT DISTINCT(FMGR.Name) FROM (SELECT FE.emp_no,FE.birth_date, CONCAT(FE.last_name, " ", FE.first_name) AS Name, FE.gender FROM (SELECT * FROM employees WHERE gender='F' AND  birth_date{'<'}'1990-01-01') FE JOIN dept_manager DE ON FE.emp_no = DE.emp_no) FMGR JOIN salaries S ON FMGR.emp_no = S.emp_no WHERE S.salary{'>'}80000;</h5>
    <Paper elevation={3} style={paperStyle}>

      {query4.map(query4=>(
        <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}}  key={query4.name}>
         Name : {query4.name}
        </Paper>
      ))
      }
    </Paper>
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
                <th>Emp1 ID</th>
                <th>Department D1</th>
                <th>Emp3 ID</th>
                <th>Department D2</th>
                <th>Emp2 ID</th>
              </tr>
            </thead>
          </table>
          <table className="table">
            <thead>
              <tr>
                <td>{query6.e1_emp}</td>
                <td><spacing>{query5.e1_dept}</spacing></td>
                <td>{query6.e3_emp}</td>
                <td><spacing>{query6.e2_dept}</spacing></td>
                <td>{query6.e2_emp}</td>
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