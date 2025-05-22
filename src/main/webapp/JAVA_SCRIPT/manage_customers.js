window.addEventListener('DOMContentLoaded', () => {
	document.querySelectorAll('.approve-btn').forEach(btn => {
		btn.addEventListener('click', e => {
			if (!confirm('Approve this customer?')) e.preventDefault();
		});
	});
	document.querySelectorAll('.reject-btn').forEach(btn => {
		btn.addEventListener('click', e => {
			if (!confirm('Reject this customer?')) e.preventDefault();
		});
	});
});