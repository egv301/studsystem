import { useEffect, useState } from 'react';
import EntityList from '../components/EntityList.jsx';

const emptySubject = { title: '', teacher: '' };

export default function SubjectsPage({ api }) {
  const [subjects, setSubjects] = useState([]);
  const [teachers, setTeachers] = useState([]);
  const [form, setForm] = useState(emptySubject);

  const load = () => Promise.all([
    api.get('/api/admin/subject-list').then(setSubjects),
    api.get('/api/admin/showAddSubjectForm').then(setTeachers)
  ]);

  useEffect(() => {
    load();
  }, []);

  async function save(event) {
    event.preventDefault();
    const body = { ...form, teacher: Number(form.teacher) };
    if (form.id) {
      await api.put('/api/admin/update-subject', body, 'Subject updated');
    } else {
      await api.post('/api/admin/add-subject', body, 'Subject added');
    }
    setForm(emptySubject);
    load();
  }

  return (
    <section className="card">
      <h2>Subjects</h2>
      <form className="grid" onSubmit={save}>
        <label>Title<input value={form.title} onChange={(event) => setForm({ ...form, title: event.target.value })} required /></label>
        <label>Teacher<select value={form.teacher} onChange={(event) => setForm({ ...form, teacher: event.target.value })} required>
          <option value="">Select teacher</option>
          {teachers.map((teacher) => <option key={teacher.id} value={teacher.id}>{teacher.firstname} {teacher.lastname}</option>)}
        </select></label>
        <button>{form.id ? 'Update' : 'Add'} subject</button>
      </form>
      <EntityList
        items={subjects}
        render={(subject) => subject.title}
        onEdit={(subject) => setForm({ id: subject.id, title: subject.title, teacher: subject.teacher || '' })}
        onDelete={(subject) => api.del(`/api/admin/delete-subject/${subject.id}`, null, 'Subject deleted').then(load)}
      />
    </section>
  );
}
