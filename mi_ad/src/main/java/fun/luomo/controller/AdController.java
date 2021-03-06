package fun.luomo.controller;
import java.util.Map;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import fun.luomo.pojo.Ad;
import fun.luomo.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/ad")
public class AdController {

	@Autowired
	private AdService adService;
	
	
	/**
	 * 查询全部广告数据
	 * @return
	 */
	@GetMapping("/ad")
	public Result findAll(){
		return new Result(true, 0, StatusCode.OK,"查询成功",adService.findAll());
	}

	/**
	 * 查询全部轮播图数据
	 * @return
	 */
	@GetMapping("/swipe")
	public Result findAllSwipe(){
		return new Result(true, 0, StatusCode.OK,"查询成功",adService.findAllSwipe());
	}

	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true, 0, StatusCode.OK,"查询成功",adService.findById(id));
	}

	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Ad> pageList = adService.findSearch(searchMap, page, size);
		return  new Result(true, 0, StatusCode.OK,"查询成功",  new PageResult<Ad>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true, 0, StatusCode.OK,"查询成功",adService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param ad
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Ad ad  ){
		adService.add(ad);
		return new Result(true, 0, StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param ad
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Ad ad, @PathVariable String id ){
		ad.setId(id);
		adService.update(ad);		
		return new Result(true, 0, StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		adService.deleteById(id);
		return new Result(true, 0, StatusCode.OK,"删除成功");
	}
	
}
