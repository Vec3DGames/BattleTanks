package xyz.vec3d.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class MainGame extends Game {

	private AssetManager assetManager = new AssetManager();

	private static MainGame _instance = new MainGame();

	public MainGame() {
		_instance = this;
	}

	private static MainGame getInstance() {
		if (_instance == null)
			return new MainGame();

		return _instance;
	}

	private static AssetManager getAssetManager() {
		return getInstance().assetManager;
	}

	private void loadAssets() {
		getAssetManager().setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		getAssetManager().setLoader(Skin.class, new SkinLoader(new InternalFileHandleResolver()));
		FileHandle dirHandle;
		if (Gdx.app.getType() == Application.ApplicationType.Android) {
			dirHandle = Gdx.files.internal("/");
		} else {
			dirHandle = Gdx.files.internal("./managed_assets/");
		}
		Array<FileHandle> handles = new Array<>();
		getHandles(dirHandle, handles);
		for (FileHandle handle : handles) {
			if (handle.name().contains("definitions"))
				continue;
			Class<?> classToLoadAs = Texture.class;
			switch (handle.extension()) {
				case "atlas":
					classToLoadAs = TextureAtlas.class;
					break;
				case "json":
					classToLoadAs = Skin.class;
					break;
				case "tmx":
					classToLoadAs = TiledMap.class;
					break;
			}
			getAssetManager().load(handle.path(), classToLoadAs);
			System.out.println("Loaded asset: " + handle.name());
		}

		boolean isLoading = true;
		while (isLoading) {
			if (getAssetManager().update()) {
				isLoading = false;
				setScreen(new MenuScreen(this));
			}
		}
	}

	/**
	 * Used to get file handles for all files in a directory.
	 *
	 * @param begin FileHandle representing the root directory.
	 * @param handles Array of FileHandles that is recursively passed to function.
	 */
	private void getHandles(FileHandle begin, Array<FileHandle> handles) {
		FileHandle[] newHandles = begin.list();
		for (FileHandle f : newHandles)
		{
			if (f.isDirectory())
			{
				getHandles(f, handles);
			}
			else
			{
				handles.add(f);
			}
		}
	}

	static <T> T getAsset(String name) {
		return getAsset(name, true);
	}

	private static <T> T getAsset(String name, boolean managed) {
		if (managed) {
			return getAssetManager().get("./managed_assets/" + name);
		}
		return getAssetManager().get(name);
	}

	@Override
	public void create () {
		loadAssets();
	}

	@Override
	public void render () {
		super.render();
	}
}
