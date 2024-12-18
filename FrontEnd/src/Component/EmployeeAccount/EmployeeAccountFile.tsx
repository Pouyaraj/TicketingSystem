import React from 'react'
import { useNavigate } from 'react-router-dom'

function EmployeeAccountFile() {
  const navigate = useNavigate();
  return (
    <div className='container'>
      <div className='button-container'>
        <button className='button' onClick={()=>navigate('/create-ticket')}>Create a Ticket</button>
        <button className='button' onClick={()=>navigate('/view-tickets')}>View Previous Tickets</button>
      </div>
    </div>
  )
}

export default EmployeeAccountFile