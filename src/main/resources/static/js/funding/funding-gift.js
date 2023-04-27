$('.itemOpCard').eq(0).css('border', '1px solid red');

var count = 0;
var $selectType;

$('.itemOpCard').on('click', function() {
    $(this).children().prop('checked', true);
    $(this).css('border', '1px solid red');

    for(let i = 0; i < $('.itemOpCard').length; i++) {
        if($('.itemOpCard')[i] == this) {
            continue;
        }
        $('.itemOpCard').eq(i).css('border', 'none');
        $('.itemOpCard').eq(i).children().prop('checked', false);
    }

    if($(this).find('.option-text').text() == '주관식') {
        $('.sectionStyle').eq(2).show();
        $selectType = $(this).find('.option-text').text();
    } else {
        $('.sectionStyle').eq(2).hide();
        $('.sectionStyle').eq(2).find('textarea').val('');
        $('.removableTag').each((i, e) => {
            $(e).remove();
        });
        $('.saveButton').attr('disabled', true);
    }
    
    if($(this).find('.option-text').text() == '객관식') {
        $('.sectionStyle').eq(3).show();
        $selectType = $(this).find('.option-text').text();
    } else {
        $('.sectionStyle').eq(3).hide();
        $('.sectionStyle').eq(3).find('textarea').val('');
        $('.saveButton').attr('disabled', true);
    }
});

$('.innerInput, .textareaJu').on('keyup', () => {
    if($('.innerInput').val() && ($('.textareaJu').val() || $('.removableTag').length >= 2)) {
        $('.saveButton').attr('disabled', false);
    } else {
        $('.saveButton').attr('disabled', true);
    }
});

$('.innerInput').on('input', function() {
    var countText = $(this).val().length;
    $('.countLength').eq(0).text(countText);
});

$('.textareaJu').on('input', function() {
    var countText = $(this).val().length;
    $('.countLength').eq(1).text(countText);
});

$('.textareaGaek').on('input', function() {
    var countText = $(this).val().length;
    $('.countLength').eq(2).text(countText);
});

$('.textareaGaek').keypress(function(e) {
    if (e.which == 13) {
        e.preventDefault();

        let text = `
            <span class="removableTag">
                <span class="value">` + $(this).val() + `</span>
                <button type="button" id="close-button" onclick="closeButton(this)">
                    <div name="close-1" class="iconSVG">
                        <svg viewBox="0 0 48 48">
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M45.1766 41.0846L28.1097 24.0186L45.1766 6.95266C46.2744 5.85485 46.2744 4.05843 45.1766 2.86082C44.0778 1.66321 42.2824 1.76401 41.0838 2.86082L24.0189 19.9268L6.95301 2.86082C5.8552 1.76401 4.05879 1.76401 2.86119 2.86082C1.66358 3.95863 1.76338 5.75605 2.86119 6.95266L19.9271 24.0186L2.86119 41.0846C1.76338 42.1834 1.76338 43.9788 2.86119 45.1774C3.95899 46.2742 5.7554 46.2742 6.95301 45.1774L24.0189 28.1104L41.0838 45.1774C42.1826 46.2742 43.979 46.2742 45.1766 45.1774C46.2744 43.9788 46.2744 42.1834 45.1766 41.0846Z">
                            </path>
                        </svg>
                    </div>
                </button>
            </span>
        `;

    
        $('.inputWrapper').next().append(text);
        if($('.removableTag').length >= 2 && $('.innerInput').val()) {
            $('.saveButton').attr('disabled', false);
        } else {
            $('.saveButton').attr('disabled', true);
        }
    }
});

$('.resetButton').on('click', function() {
    $('.innerInput').val('');
    $('.textareaStyle').val('');
    $('.removableTag').each((i, e) => {
        $(e).remove();
    });
    $('.countLength').each((i, e) => {
        $(e).text('0');
    });
    $('.saveButton').attr('disabled', true);
});

function closeButton(e) {
    $(e).parent().remove();

    if($('.removableTag').length >= 2 && $('.innerInput').val()) {
        $('.saveButton').attr('disabled', false);
    } else {
        $('.saveButton').attr('disabled', true);
    }
};

function removeButton(e) {
    $(e).parent().remove();
    $('.item-count').text(--count);
};

$('.saveButton').on('click', () => {
    let $itemTitle = $('.innerInput').val();
    let explainCount = ++count;
    let content = $('.textareaJu').val();
    $('.value').each((i, e) => {
        console.log(i);
        content = content + $(e).text() + `<br>`;
    });

    let text = `
        <li class="previewItemStyel previewItemStyel">
            <button type="button" value="7513af07-bd37-48b3-9cee-28f6c588d2a2" class="previewItemButton">
                <strong>` + $itemTitle + `</strong>
                <span class="select-type">` + $selectType + `</span> 옵션<span class="explain-count">(` + explainCount + `)</span>
                <ul>
                    <li>` + content + `</li>
                </ul>
            </button>
            <button type="button" value="7513af07-bd37-48b3-9cee-28f6c588d2a2" class="removeButton" onclick="removeButton(this)">
                <div name="delete" class="iconSVG">
                    <svg viewBox="0 0 48 48">
                        <path fill-rule="evenodd" clip-rule="evenodd"
                            d="M38.814 42.172C38.814 42.946 38.064 43.574 37.144 43.574H10.856C9.936 43.574 9.186 42.946 9.186 42.172V12.218H38.814V42.172ZM17.564 4.426L30.542 4.524V9.794H17.462L17.564 4.426ZM44.786 9.794H32.968V4.524C32.968 3.13 31.832 2 30.436 2H17.564C16.168 2 15.03 3.13 15.03 4.524V9.794H3.212C2.542 9.794 2 10.336 2 11.006C2 11.676 2.542 12.218 3.212 12.218H6.76V42.172C6.76 44.284 8.598 46 10.856 46H37.144C39.402 46 41.24 44.284 41.24 42.172V12.218H44.786C45.456 12.218 46 11.676 46 11.006C46 10.336 45.456 9.794 44.786 9.794ZM18.857 36.9338C19.527 36.9338 20.069 36.3918 20.069 35.7218V20.0738C20.069 19.4038 19.527 18.8618 18.857 18.8618C18.187 18.8618 17.645 19.4038 17.645 20.0738V35.7218C17.645 36.3918 18.187 36.9338 18.857 36.9338ZM30.3542 35.7218C30.3542 36.3918 29.8122 36.9338 29.1422 36.9338C28.4722 36.9338 27.9302 36.3918 27.9302 35.7218V20.0738C27.9302 19.4038 28.4722 18.8618 29.1422 18.8618C29.8122 18.8618 30.3542 19.4038 30.3542 20.0738V35.7218Z">
                        </path>
                    </svg>
                </div>
            </button>
        </li>
    `;

    $('.previewItem').hide();
    $('.result-ul').append(text);
    $('.item-count').text(count);
});