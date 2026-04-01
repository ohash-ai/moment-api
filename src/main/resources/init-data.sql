-- 示例 moment 数据（手动执行初始化）
INSERT INTO moment (date, image_url, quote, source, description, is_featured, like_count)
VALUES
('2026.04.01', 'https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=800', '山川异域，风月同天', '佚名', '那些看似遥远的风景，其实一直都在心里。', 1, 0),
('2026.03.28', 'https://images.unsplash.com/photo-1501854140801-50d01698950b?w=800', '愿你出走半生，归来仍是少年', '岳云鹏', '走过很多地方，见过很多人，最终还是回到最初的自己。', 0, 0),
('2026.03.20', 'https://images.unsplash.com/photo-1470071459604-3b5ec3a7fe05?w=800', '不必追赶，各有各的时区', '佚名', '有人25岁就结婚了，有人35岁才找到热爱的事业，每个人都有自己的节奏。', 0, 0);

-- 配置数据
INSERT INTO app_config (config_key, config_value)
VALUES ('bottomQuote', '愿你平静如初，温柔如故')
ON DUPLICATE KEY UPDATE config_value = VALUES(config_value);
