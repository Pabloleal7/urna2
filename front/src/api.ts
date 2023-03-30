import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://apiurna-env.eba-m58zxm2n.us-east-1.elasticbeanstalk.com',
  timeout: 1000,
});


instance.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

instance.interceptors.response.use(
  (response) => response,
  async (error) => {
    const originalRequest = error.config;
    if (
      error.response.status === 401 &&
      !originalRequest._retry &&
      error.config.url !== '/api/login'
    ) {
      originalRequest._retry = true;
      try {
        const response = await axios.post('/api/refresh', {
          refreshToken: localStorage.getItem('refreshToken'),
        });
        localStorage.setItem('token', response.data.token);
        localStorage.setItem('refreshToken', response.data.refreshToken);
        return instance(originalRequest);
      } catch (error) {
        console.log(error);
      }
    }
    throw error;
  }
);

export default instance;
