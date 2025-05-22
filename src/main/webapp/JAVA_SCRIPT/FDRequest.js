document.addEventListener('DOMContentLoaded', () => {
	const typeEl = document.getElementById('type');
	const tenureEl = document.getElementById('tenure');
	const rateEl = document.getElementById('rate');

	const RATES = {
		'FD-6': 5.0, 'FD-12': 6.0, 'FD-24': 6.5, 'FD-36': 7.0,
		'RD-6': 4.5, 'RD-12': 5.0, 'RD-24': 5.5, 'RD-36': 6.0
	};

	function updateRate() {
		const key = `${typeEl.value}-${tenureEl.value}`;
		rateEl.value = RATES[key] ? RATES[key] + '%' : '';
	}

	typeEl.addEventListener('change', updateRate);
	tenureEl.addEventListener('input', updateRate);
});
