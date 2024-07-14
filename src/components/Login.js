import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toastError } from '../api/ToastService';
import { loginUser } from '../api/AuthService';

function Login() {
  const [values, setValues] = useState({ email: '', password: '' });
  const navigate = useNavigate();

  const onChange = (event) => {
    setValues({ ...values, [event.target.name]: event.target.value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await loginUser(values);
      navigate('/contacts');
    } catch (error) {
      toastError(error.message);
    }
  };

  return (
    <div className="login-container">
      <form onSubmit={handleSubmit}>
        <h2>Login</h2>
        <div className="input-box">
          <span className="details">Email</span>
          <input type="email" name="email" value={values.email} onChange={onChange} required />
        </div>
        <div className="input-box">
          <span className="details">Password</span>
          <input type="password" name="password" value={values.password} onChange={onChange} required />
        </div>
        <div className="form-footer">
          <button type="submit" className="btn">Login</button>
          <button type="button" className="btn" onClick={() => navigate('/register')}>Register</button>
        </div>
      </form>
    </div>
  );
}

export default Login;
