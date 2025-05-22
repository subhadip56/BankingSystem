// loanRequest.js
// -----------------
// On the Loan Request page, this script reads the embedded JSON of 
// { loanType: rate% } and auto‑fills the “Interest Rate” field
// whenever the user picks a different loan type.

document.addEventListener('DOMContentLoaded', () => {
	const ratesDataEl = document.getElementById('loanRatesData');
	if (!ratesDataEl) return;  // nothing to do if not on LoanRequest.jsp

	// Parse the JSON blob placed into the JSP:
	const rates = JSON.parse(ratesDataEl.textContent);

	const loanTypeEl = document.getElementById('loanType');
	const rateEl = document.getElementById('rate');

	loanTypeEl.addEventListener('change', () => {
		const selected = loanTypeEl.value;
		rateEl.value = selected && rates[selected] !== undefined
			? rates[selected]
			: '';
	});
});
