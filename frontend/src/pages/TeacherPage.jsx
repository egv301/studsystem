import { useEffect, useState } from 'react';
import TeacherSubjectPage from './TeacherSubjectPage.jsx';

export default function TeacherPage({ api }) {
  const [subjects, setSubjects] = useState([]);
  const [selected, setSelected] = useState('');

  useEffect(() => {
    api.get('/api/teacher/teachers-subjects').then(setSubjects);
  }, []);

  return (
    <section className="card">
      <h2>Teacher workspace</h2>
      <label>Subject<select value={selected} onChange={(event) => setSelected(event.target.value)}>
        <option value="">Select subject</option>
        {subjects.map((subject) => <option key={subject.id} value={subject.id}>{subject.title}</option>)}
      </select></label>
      {selected && <TeacherSubjectPage api={api} subjectId={selected} />}
    </section>
  );
}
