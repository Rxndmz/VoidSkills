package me.rxndmz.voidskills;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLHandler {

    private VoidSkills plugin = VoidSkills.getPlugin();

    public void createTables() {
        PreparedStatement ps;
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS skills (UUID VARCHAR(100), TOTAL_LVL INT, MINING_LVL INT, MINING_XP INT, MINING_SP INT, FARMING_LVL INT, FARMING_XP INT, FARMING_SP INT, FISHING_LVL INT, FISHING_XP INT, FISHING_SP INT, HUNTING_LVL INT, HUNTING_XP INT, HUNTING_SP INT)");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //
    //  Skill Data
    //

    public void setupSkillData(Player player) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO skills (UUID, TOTAL_LVL, MINING_LVL, MINING_XP, MINING_SP, FARMING_LVL, FARMING_XP, FARMING_SP, FISHING_LVL, FISHING_XP, FISHING_SP, HUNTING_LVL, HUNTING_XP, HUNTING_SP) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, player.getUniqueId().toString()); // UUID
            ps.setInt(2, 4); // TOTAL LVL
            ps.setInt(3, 1); // MINING LVL
            ps.setInt(4, 0); // MINING XP
            ps.setInt(5, 0); // MINING SP
            ps.setInt(6, 1); // FARMING LVL
            ps.setInt(7, 0); // FARMING XP
            ps.setInt(8, 0); // FARMING SP
            ps.setInt(9, 1); // FISHING LVL
            ps.setInt(10, 0); // FISHING XP
            ps.setInt(11, 0); // FISHING SP
            ps.setInt(12, 1); // HUNTING LVL
            ps.setInt(13, 0); // HUNTING XP
            ps.setInt(14, 0); // HUNTING SP
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean skillDataExists(Player player) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM skills WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());

            ResultSet results = ps.executeQuery();
            if (results.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //
    // Mining
    //

    // Setters

    public void setMiningLvl (Player player, int lvl) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE skills SET MINING_LVL=? WHERE UUID=?");
            ps.setInt(1, lvl);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMiningXp (Player player, int xp) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE skills SET MINING_XP=? WHERE UUID=?");
            ps.setInt(1, xp);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMiningSp (Player player, int sp) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE skills SET MINING_SP=? WHERE UUID=?");
            ps.setInt(1, sp);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getters

    public int getMiningLvl(Player player) {
        int lvl = 0;
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT MINING_LVL FROM skills WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lvl = rs.getInt("MINING_LVL");
                return lvl;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lvl;
    }

    public int getMiningXp(Player player) {
        int xp = 0;
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT MINING_XP FROM skills WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                xp = rs.getInt("MINING_XP");
                return xp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return xp;
    }

    public int getMiningSp(Player player) {
        int sp = 0;
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT MINING_SP FROM skills WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sp = rs.getInt("MINING_SP");
                return sp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sp;
    }

    //
    // Farming
    //

    // Setters

    public void setFarmingLvl (Player player, int lvl) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE skills SET FARMING_LVL=? WHERE UUID=?");
            ps.setInt(1, lvl);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFarmingXp (Player player, int xp) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE skills SET FARMING_XP=? WHERE UUID=?");
            ps.setInt(1, xp);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFarmingSp (Player player, int sp) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE skills SET FARMING_SP=? WHERE UUID=?");
            ps.setInt(1, sp);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getters

    public int getFarmingLvl(Player player) {
        int lvl = 0;
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT FARMING_LVL FROM skills WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lvl = rs.getInt("FARMING_LVL");
                return lvl;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lvl;
    }

    public int getFarmingXp(Player player) {
        int xp = 0;
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT FARMING_XP FROM skills WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                xp = rs.getInt("FARMING_XP");
                return xp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return xp;
    }

    public int getFarmingSp(Player player) {
        int sp = 0;
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT FARMING_SP FROM skills WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sp = rs.getInt("FARMING_SP");
                return sp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sp;
    }

    //
    // Fishing
    //

    // Setters

    public void setFishingLvl (Player player, int lvl) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE skills SET FISHING_LVL=? WHERE UUID=?");
            ps.setInt(1, lvl);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFishingXp (Player player, int xp) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE skills SET FISHING_XP=? WHERE UUID=?");
            ps.setInt(1, xp);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setFishingSp (Player player, int sp) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE skills SET FISHING_SP=? WHERE UUID=?");
            ps.setInt(1, sp);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getters

    public int getFishingLvl(Player player) {
        int lvl = 0;
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT FISHING_LVL FROM skills WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lvl = rs.getInt("FISHING_LVL");
                return lvl;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lvl;
    }

    public int getFishingXp(Player player) {
        int xp = 0;
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT FISHING_XP FROM skills WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                xp = rs.getInt("FISHING_XP");
                return xp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return xp;
    }

    public int getFishingSp(Player player) {
        int sp = 0;
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT FISHING_SP FROM skills WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sp = rs.getInt("FISHING_SP");
                return sp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sp;
    }

    //
    // Hunting
    //

    // Setters

    public void setHuntingLvl (Player player, int lvl) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE skills SET HUNTING_LVL=? WHERE UUID=?");
            ps.setInt(1, lvl);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setHuntingXp (Player player, int xp) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE skills SET HUNTING_XP=? WHERE UUID=?");
            ps.setInt(1, xp);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setHuntingSp (Player player, int sp) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE skills SET HUNTING_SP=? WHERE UUID=?");
            ps.setInt(1, sp);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getters

    public int getHuntingLvl(Player player) {
        int lvl = 0;
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT HUNTING_LVL FROM skills WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lvl = rs.getInt("HUNTING_LVL");
                return lvl;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lvl;
    }

    public int getHuntingXp(Player player) {
        int xp = 0;
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT HUNTING_XP FROM skills WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                xp = rs.getInt("HUNTING_XP");
                return xp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return xp;
    }

    public int getHuntingSp(Player player) {
        int sp = 0;
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT HUNTING_SP FROM skills WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sp = rs.getInt("HUNTING_SP");
                return sp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sp;
    }

    //
    // Total Level
    //

    public void setTotalLvl (Player player, int lvl) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE skills SET TOTAL_LVL=? WHERE UUID=?");
            ps.setInt(1, lvl);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getTotalLevel(Player player) {
        int lvl = 0;
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT TOTAL_LVL FROM skills WHERE UUID=?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lvl = rs.getInt("TOTAL_LVL");
                return lvl;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lvl;
    }

    //
    // Reset Skills
    //

    public void resetSkills(Player player) {
        setTotalLvl(player, 4);
        setMiningLvl(player, 1);
        setMiningXp(player, 0);
        setMiningSp(player, 0);
        setFarmingLvl(player, 1);
        setFarmingXp(player, 0);
        setFarmingSp(player, 0);
        setFishingLvl(player, 1);
        setFishingXp(player, 0);
        setFishingSp(player, 0);
        setHuntingLvl(player, 1);
        setHuntingXp(player, 0);
        setHuntingSp(player, 0);
    }

}
