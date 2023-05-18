myRicePayment.forEach((myrice, i) => {
    let text;

    text = `
        <tr>
            <td>${myrice.memberDTO.memberRice}</td>
            <td>${myrice.ricePaymentUsed}석</td>
            <td>${myrice.createdDate.substring(0, 10)} ${myrice.createdDate.substring(11, 19)}</td>
            <td>${myrice.ricePaymentStatus}</td>
        </tr>
        `
    $('tbody').append(text);
})