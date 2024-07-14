import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toastError, toastSuccess } from '../api/ToastService';
import { registerUser } from '../api/AuthService';

function Register() {
  const [values, setValues] = useState({ name: '', email: '', password: '' });
  const navigate = useNavigate();

  const onChange = (event) => {
    setValues({ ...values, [event.target.name]: event.target.value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await registerUser(values);
      toastSuccess('Registration successful. Please log in.');
      navigate('/');
    } catch (error) {
      toastError(error.message);
    }
  };

  return (
    <div className="register-container">
      <form onSubmit={handleSubmit}>
        <h2>Register</h2>
        <div className="input-box">
          <span className="details">Name</span>
          <input type="text" name="name" value={values.name} onChange={onChange} required />
        </div>
        <div className="input-box">
          <span className="details">Email</span>
          <input type="email" name="email" value={values.email} onChange={onChange} required />
        </div>
        <div className="input-box">
          <span className="details">Password</span>
          <input type="password" name="password" value={values.password} onChange={onChange} required />
        </div>
        <div className="form-footer">
          <button type="submit" className="btn">Register</button>
          <button type="button" className="btn" onClick={() => navigate('/')}>Back to Login</button>
        </div>
      </form>
    </div>
  );
}

export default Register;
