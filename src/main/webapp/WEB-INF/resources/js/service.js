function deleteProduct(url, id) {
    if (confirm("Bạn có chắc muốn xóa dịch vụ này không?")) {
        fetch(url, {
            method: 'delete'
        }).then(res => {
            if (res.status === 204) {
                alert("Xóa dịch vụ thành công.");
                location.reload();
            } else {
                alert("Xảy ra lỗi khi xóa dịch vụ.");
            }
        }).catch(error => {
            console.error("Lỗi: ", error);
            alert("Xảy ra lỗi khi xóa dịch vụ.");
        });
    }
}

// Hàm thực hiện tìm kiếm
function searchProduct() {
    var keyword = document.getElementById('searchInput').value.trim();
    var url = '/services?kw=' + encodeURIComponent(keyword);

    fetch(url)
        .then(function(response) {
            if (response.status === 200) {
                console.log('Tìm kiếm thành công!');
                location.reload();
            } else if (response.status === 404) {
                console.log('Không tìm thấy dịch vụ nào phù hợp.');
            } else {
                console.error('Có lỗi xảy ra khi tìm kiếm dịch vụ.');
                alert('Có lỗi xảy ra khi tìm kiếm dịch vụ.');
            }
        })
        .catch(function(error) {
            // Xử lý lỗi nếu có
            console.error(error);
            alert('Có lỗi xảy ra khi tìm kiếm dịch vụ.');
        });
}