import { useState } from 'react';
import GroupsPage from './GroupsPage.jsx';
import StudentsPage from './StudentsPage.jsx';
import SubjectsPage from './SubjectsPage.jsx';
import StudentSubjectsPage from './StudentSubjectsPage.jsx';

const tabs = ['groups', 'students', 'subjects', 'student subjects'];

export default function AdminPage({ api }) {
  const [tab, setTab] = useState('groups');

  return (
    <>
      <div className="tabs">
        {tabs.map((name) => (
          <button key={name} className={`tab ${tab === name ? 'active' : ''}`} onClick={() => setTab(name)}>
            {name}
          </button>
        ))}
      </div>
      {tab === 'groups' && <GroupsPage api={api} />}
      {tab === 'students' && <StudentsPage api={api} />}
      {tab === 'subjects' && <SubjectsPage api={api} />}
      {tab === 'student subjects' && <StudentSubjectsPage api={api} />}
    </>
  );
}
