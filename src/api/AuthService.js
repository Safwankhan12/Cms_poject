import axios from 'axios';

const API_URL = 'http://localhost:5000/api/auth';  // Update this to your actual backend API URL

export const loginUser = async (userData) => {
  return await axios.post(`${API_URL}/login`, userData);
};

export const registerUser = async (userData) => {
  return await axios.post(`${API_URL}/register`, userData);
};
