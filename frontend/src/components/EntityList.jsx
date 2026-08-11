export default function EntityList({ items, render, onEdit, onDelete }) {
  return (
    <div className="list">
      {items.map((item) => (
        <div className="item" key={item.id}>
          <span>{render(item)}</span>
          <div className="actions">
            {onEdit && <button className="secondary" onClick={() => onEdit(item)}>Edit</button>}
            {onDelete && <button className="danger" onClick={() => onDelete(item)}>Delete</button>}
          </div>
        </div>
      ))}
    </div>
  );
}
