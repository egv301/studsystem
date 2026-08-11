import { useMemo, useState } from 'react';
import { createApi } from './api/client.js';
import AuthPage from './pages/AuthPage.jsx';
import AdminPage from './pages/AdminPage.jsx';
import TeacherPage from './pages/TeacherPage.jsx';

export default function App() {
  const [auth, setAuth] = useState(() => JSON.parse(localStorage.getItem('auth') || 'null'));
  const [message, setMessage] = useState(null);
  const api = useMemo(() => createApi(auth, setMessage), [auth]);

  function saveAuth(nextAuth) {
    setAuth(nextAuth);
    if (nextAuth) {
      localStorage.setItem('auth', JSON.stringify(nextAuth));
      return;
    }
    localStorage.removeItem('auth');
  }

  return (
    <main className="app">
      <div className="row">
        <h1>Student Management System</h1>
        {auth && <button className="secondary" onClick={() => saveAuth(null)}>Logout</button>}
      </div>
      {message && <div className={`message ${message.type}`}>{message.text}</div>}
      {!auth && <AuthPage api={api} onAuth={saveAuth} />}
      {auth?.roleName === 'ROLE_ADMIN' && <AdminPage api={api} />}
      {auth?.roleName === 'ROLE_TEACHER' && <TeacherPage api={api} />}
      {auth && !['ROLE_ADMIN', 'ROLE_TEACHER'].includes(auth.roleName) && (
        <div className="card">Unsupported role: {auth.roleName}</div>
      )}
    </main>
  );
}
