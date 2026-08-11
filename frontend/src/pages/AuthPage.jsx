import { useState } from 'react';

export default function AuthPage({ api, onAuth }) {
  const [mode, setMode] = useState('login');
  const [form, setForm] = useState({});

  function update(field, value) {
    setForm({ ...form, [field]: value });
  }

  async function submit(event) {
    event.preventDefault();
    if (mode === 'login') {
      const auth = await api.post('/api/login', form);
      onAuth(auth);
      return;
    }
    await api.post('/api/registration', form, 'Registration completed. Login with the new account.');
    setMode('login');
    setForm({});
  }

  return (
    <section className="card">
      <div className="tabs">
        <button className={`tab ${mode === 'login' ? 'active' : ''}`} onClick={() => setMode('login')}>Login</button>
        <button className={`tab ${mode === 'registration' ? 'active' : ''}`} onClick={() => setMode('registration')}>Register</button>
      </div>
      <form className="grid" onSubmit={submit}>
        <label>Username<input value={form.username || ''} onChange={(event) => update('username', event.target.value)} required /></label>
        {mode === 'registration' && (
          <label>Email<input value={form.email || ''} onChange={(event) => update('email', event.target.value)} required /></label>
        )}
        <label>Password<input type="password" value={form.password || ''} onChange={(event) => update('password', event.target.value)} required /></label>
        {mode === 'registration' && (
          <label>Confirm password<input type="password" value={form.confirmPassword || ''} onChange={(event) => update('confirmPassword', event.target.value)} required /></label>
        )}
        <button>{mode === 'login' ? 'Login' : 'Register'}</button>
      </form>
    </section>
  );
}
