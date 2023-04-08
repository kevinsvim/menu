package com.zsh.resource.controller;

import com.zsh.common.result.CommonResult;
import com.zsh.resource.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zsh on 2023/4/4
 *
 * @author zsh
 */
@RestController
@RequestMapping("/resource/game")
public class GameController {

    private final GameService gameService;
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    @GetMapping("/getGames")
    public CommonResult<Object> getAllGame() {
        return CommonResult.success(gameService.list());
    }
}
