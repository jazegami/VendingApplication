package ajun.webapp.vebnding.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_change_manage")
public class TChangeManage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "change_manage_id")
	private Long changeManageId;
	
	@Column(name = "ten_coin")
	private int tenCoin;
	
	@Column(name = "fifty_coin")
	private int fiftyCoin;
	
	@Column(name = "onehundred_coin")
	private int onehundredCoin;
	
	@Column(name = "fivehundred_coin")
	private int fivehundredCoin;
	
	@Column(name = "onethousand_bill")
	private int onethousandBill;
	
	@Column(name = "create_date")
	private Timestamp createDate;
	
	@Column(name = "update_date")
	private Timestamp updateDate;
	
	@Column(name = "delete_flg")
	private String deleteFlg;
	
	

	public Long getChangeManageId() {
		return changeManageId;
	}

	public void setChangeManageId(Long changeManageId) {
		this.changeManageId = changeManageId;
	}

	public int getTenCoin() {
		return tenCoin;
	}

	public void setTenCoin(int tenCoin) {
		this.tenCoin = tenCoin;
	}

	public int getFiftyCoin() {
		return fiftyCoin;
	}

	public void setFiftyCoin(int fiftyCoin) {
		this.fiftyCoin = fiftyCoin;
	}

	public int getOnehundredCoin() {
		return onehundredCoin;
	}

	public void setOnehundredCoin(int onehundredCoin) {
		this.onehundredCoin = onehundredCoin;
	}

	public int getFivehundredCoin() {
		return fivehundredCoin;
	}

	public void setFivehundredCoin(int fivehundredCoin) {
		this.fivehundredCoin = fivehundredCoin;
	}

	public int getOnethousandBill() {
		return onethousandBill;
	}

	public void setOnethousandBill(int onethousandBill) {
		this.onethousandBill = onethousandBill;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
}
